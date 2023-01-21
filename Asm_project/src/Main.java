import Functions.Functions;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(1);
        integers.add(1);
        int t =Functions.lsToUSBinary(integers);
        int t2 = Functions.lsToSBinary(integers);
        System.out.println(t + "  " + t2);

    }
}