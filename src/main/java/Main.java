import utils.FileReaderAndWriter;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FileReaderAndWriter.removeOldValues();
        VimosParser vimosParser = new VimosParser();
        vimosParser.parse();
//        vimosParser.parsePriceByListNomenclature();
        PetrovichParser petrovichParser = new PetrovichParser();
        petrovichParser.parse();
//        petrovichParser.parsePriceByListNomenclature();
//        SevlesSpbParser spbParser = new SevlesSpbParser();
//        spbParser.parse();
//        spbParser.parsePriceByListNomenclature();
//        TdlesovikParser tdlesovikParser = new TdlesovikParser();
//        tdlesovikParser.parse();
//        tdlesovikParser.parsePriceByListNomenclature();
//        BriketiSpb briketiSpb = new BriketiSpb();
//        briketiSpb.parseToFile();
//        GoodWood goodWood = new GoodWood();
//        goodWood.parseToFile();
    }

}
