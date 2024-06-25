package Stuff.Hash;

import Main.Main;
import java.util.List;
import java.util.zip.CRC32;

public class CRC32Checksum implements Main.Runner {
    public long checksum(String input) {
        CRC32 crc = new CRC32();
        crc.update(input.getBytes());
        return crc.getValue();
    }

    @Override
    public void run(List<String> inputs) {
        for (String input : inputs) {
            System.out.println(checksum(input));
        }
    }
}