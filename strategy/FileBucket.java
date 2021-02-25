package shortener.strategy;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/*
 * The class is used by FileStorageStrategy class for Entries storage (as files).
 */
public class FileBucket {
    private Path path;

    public FileBucket() {
        try {
            path = Files.createTempFile("file", ".tmp");
            Files.deleteIfExists(path);
            Files.createFile(path);
        } catch (IOException ignored) {
        }
        path.toFile().deleteOnExit();
    }

    public long getFileSize() {
        try {
            return Files.size(path);
        } catch (IOException ignored) {
        }
        return 0;
    }

    public void putEntry(Entry entry) {
        try (OutputStream os = Files.newOutputStream(path);
             ObjectOutputStream out = new ObjectOutputStream(os)) {
            out.writeObject(entry);
        } catch (IOException ignored) {
        }
    }

    public Entry getEntry() {
        if (getFileSize() == 0) return null;
        Entry entry = null;
        try (InputStream is = Files.newInputStream(path);
             ObjectInputStream in = new ObjectInputStream(is)) {
            entry = (Entry) in.readObject();
        } catch (IOException | ClassNotFoundException ignored) {
        }
        return entry;
    }

    public void remove() {
        try {
            Files.delete(path);
        } catch (IOException ignored) {
        }
    }

}
