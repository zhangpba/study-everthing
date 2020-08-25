package com.study.array;

public class HasStatic {

    private static int x = 100;

    public static void main(String[] args) {
        HasStatic hs1 = new HasStatic();
        hs1.x++;
        System.out.println("x1=" + x);

        HasStatic hs2 = new HasStatic();
        hs2.x++;
        System.out.println("x2=" + x);

        hs1 = new HasStatic();
        hs1.x++;
        System.out.println("x3=" + x);
        HasStatic.x--;
        System.out.println("x4=" + x);
    }

    private void getType(String assetType) {
        String url = "";
        switch (assetType) {
            case "asset":
                url += "asset";
                break;
            case "system":
                url += "system";
                break;
            case "web":
                url += "web";
                break;
            default:
                url += "asset";
        }

    }
}
