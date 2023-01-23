package Variables;

import java.util.ArrayList;

public class Registers {
    public static ArrayList<Integer> eax = new ArrayList<>();
    public static ArrayList<Integer> ebx = new ArrayList<>();
    public static ArrayList<Integer> ecx = new ArrayList<>();
    public static ArrayList<Integer> edx = new ArrayList<>();
    public static ArrayList<Integer> esi = new ArrayList<>();
    public static ArrayList<Integer> edi = new ArrayList<>();
    public static ArrayList<Integer> esp = new ArrayList<>();
    public static ArrayList<Integer> ebp = new ArrayList<>();

    public static void setReg() {
        for (int i = 0; i < 32; i++) {
            eax.add(0);
            ebx.add(0);
            ecx.add(0);
            edx.add(0);
            esi.add(0);
            edi.add(0);
            esp.add(0);
            ebp.add(0);
        }
    }


}
