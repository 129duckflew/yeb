package cn.duckflew.utils;


public class StringUtil
{
    public static String telephoneHide(String telephone)
    {
        if (telephone==null||telephone.isEmpty())return null;
        return telephone.substring(0, 3) + "****" + telephone.substring(7);
    }
    public static boolean isEmpty(String data)
    {
        return data==null||data.isEmpty();
    }
}
