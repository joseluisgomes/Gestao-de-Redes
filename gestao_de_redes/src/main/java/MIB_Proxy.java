import net.percederberg.mibble.Mib;
import net.percederberg.mibble.MibLoader;
import net.percederberg.mibble.MibLoaderException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


public class MIB_Proxy {

    public Mib loadMib(File file)

            throws FileNotFoundException, MibLoaderException {

        // In real code, a single MibLoader instance should be reused
        MibLoader loader = new MibLoader();

        // The MIB file may import other MIBs (often in same dir)
        loader.addDir(file.getParentFile());

        // Once initialized, MIB loading is straight-forward
        try {
            return loader.load(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        final String filePath = "/home/luis/GitHub/UM/Gestao-de-Redes/gestao_de_redes/";
        MIB_Proxy mib_proxy = new MIB_Proxy();
        Mib a = mib_proxy.loadMib(new File("MIB_PROXY.txt"));
    }
}
