package sh.io;

import java.io.*;

/**
 * Serialization of class object
 */
public class ReadWriteSerial {
    /**
     * write object to file
     */
    public void writeToFite(Object net) throws IOException {
        FileOutputStream fos = new FileOutputStream("networkSerial.out");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        try {
            oos.writeObject(net);
        } catch (IOException e) {
            e.printStackTrace(System.err);
        } finally {
            oos.flush();
            oos.close();
        }
    }

    /**
     * read object from file
     */
    public Object readFromFile() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("networkSerial.out");
        Object net = null;
        try (ObjectInputStream oin = new ObjectInputStream(fis)) {
            net = oin.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace(System.err);
        }
        return net;
    }
}
