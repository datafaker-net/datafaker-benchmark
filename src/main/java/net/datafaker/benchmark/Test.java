package net.datafaker.benchmark;

import net.datafaker.Faker;
import net.datafaker.providers.base.BaseFaker;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class Test {
    public static void main(String[] args) throws IOException {
        BaseFaker defaultFaker = new BaseFaker();

        System.out.println(ManagementFactory.getRuntimeMXBean().getClassPath());

        Set<String> fileMasks = new HashSet<>(Arrays.asList("datafaker"));
        Set<String> langs = new HashSet<>(Arrays.asList(Locale.getISOLanguages()));
        String[] paths = ManagementFactory.getRuntimeMXBean().getClassPath().split(":");
        Set<String> locales = new HashSet<>();
        for (String s: paths) {
            Files.walkFileTree(Paths.get(s).toAbsolutePath(), new SimpleFileVisitor<Path>() {

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    //System.out.println(file.toAbsolutePath().toString());
                    final String filename;
                    if (Files.isDirectory(file) || Files.isHidden(file) || !Files.isReadable(file)) {
                        return super.visitFile(file, attrs);
                    }
                    if ((filename = file.getFileName().toString().toLowerCase(Locale.ROOT)).endsWith(".yml") || filename.endsWith(".yaml")) {
                        final String parentFileName = file.getParent().getFileName().toString();
                        if (langs.contains(parentFileName)) {
                            locales.add(parentFileName);
                        } else {
                            locales.add(filename.substring(0, filename.indexOf('.')));
                        }
                    } else if (filename.endsWith(".jar") && fileMasks.stream().anyMatch(filename::contains)) {
                        try (FileSystem zipfs = FileSystems.newFileSystem(file, (ClassLoader) null))
                        {
                            for (Path rootPath : zipfs.getRootDirectories())
                            {
                                Files.walkFileTree(rootPath,  new SimpleFileVisitor<Path>() {

                                    @Override
                                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                                        final String filename;
                                        if (file.getNameCount() > 2 || Files.isDirectory(file) || Files.isHidden(file) || !Files.isReadable(file)) {
                                            return super.visitFile(file, attrs);
                                        }
                                        if ((filename = file.getFileName().toString()).endsWith(".yml") || filename.endsWith(".yaml")) {
                                            final Path parentFileName = file.getParent().getFileName();
                                            if (parentFileName == null) {
                                                locales.add(filename.substring(0, filename.indexOf('.')));
                                            } else {
                                                locales.add(parentFileName.toString());
                                            }
                                        }
                                        return super.visitFile(file, attrs);
                                    }
                                });
                            }
                        }
                    }
                    return super.visitFile(file, attrs);
                }
            });

        }
        System.out.println(locales);
    }
}
