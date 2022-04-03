package JavaCore.HW3_2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    private static void SaveGames(String path, GameProgress game) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream obs = new ObjectOutputStream(fos)) {
            obs.writeObject(game);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    } //метод для записи фалоы игры

    private static void ZipEntry(String s, List<String> arrayList) {
        try (ZipOutputStream z = new ZipOutputStream(new FileOutputStream(s))) {
            for (String a : arrayList) {
                try (FileInputStream f = new FileInputStream(a)) {
                    ZipEntry entry = new ZipEntry(a);
                    z.putNextEntry(entry);
                    while (f.available() > 0) {
                        z.write(f.read());
                    }
                    z.closeEntry();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    } //метод для архивирования


    public static void main(String[] args) {

        GameProgress player1 = new GameProgress(19, 3, 4, 3.14);
        GameProgress player2 = new GameProgress(1, 10, 20, 4.45);
        GameProgress player3 = new GameProgress(25, 1, 1, 1.45);

        SaveGames("D://Games//savegames//player1.dat", player1);
        SaveGames("D://Games//savegames//player2.dat", player2);
        SaveGames("D://Games//savegames//player3.dat", player3);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("D://Games//savegames//player1.dat");
        arrayList.add("D://Games//savegames//player2.dat");
        arrayList.add("D://Games//savegames//player3.dat");
        ZipEntry("D://Games//savegames//zip.zip", arrayList);
        File player1Dat = new File("D://Games//savegames//player1.dat");
        File player2Dat = new File("D://Games//savegames//player2.dat");
        File player3Dat = new File("D://Games//savegames//player3.dat");
        if (player1Dat.delete()) System.out.println("Файл \"player1.dat\" удален");
        if (player2Dat.delete()) System.out.println("Файл \"player2.dat\" удален");
        if (player3Dat.delete()) System.out.println("Файл \"player3.dat\" удален");


    }

}
