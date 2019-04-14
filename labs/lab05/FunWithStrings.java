package lab05;

import java.util.List;

public class FunWithStrings
{
    public static void swapMaxes(List<String> list1, List<String> list2)
    {
        if (list1 == null || list2 == null || list1.size() == 0 || list2.size() == 0)
            throw new IllegalArgumentException(
                    "cannot swap maxes of empty lists, null lists, or lists with null elements");

        boolean list1Null = true;

        for (var s : list1)
        {
            if (s != null)
            {
                list1Null = false;
                break;
            }
        }

        boolean list2Null = true;

        for (var s : list2)
        {
            if (s != null)
            {
                list2Null = false;
                break;
            }
        }

        if (list1Null || list2Null)
            throw new IllegalArgumentException(
                    "cannot swap maxes of empty lists, null lists, or lists with null elements");

        String b1 = "";
        int b1Index = -1;

        String b2 = "";
        int b2Index = -1;

        for (int i = 0; i < Math.max(list1.size(), list2.size()); ++i)
        {
            if (i < list1.size())
            {
                var s1 = list1.get(i);

                // >= because all non-null strings have different lengths, and to avoid the
                // edge case of passing an empty string ("") (and thus having indexes be -1
                if (s1 != null && s1.length() >= b1.length())
                {
                    b1 = s1;
                    b1Index = i;
                }
            }

            if (i < list2.size())
            {
                var s2 = list2.get(i);

                if (s2 != null && s2.length() >= b2.length())
                {
                    b2 = s2;
                    b2Index = i;
                }
            }
        }

        list1.set(b1Index, b2);
        list2.set(b2Index, b1);
    }
}
