import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by pingao on 2019/9/7.
 */
public class ShiftAnd implements Search {
    @Override
    public List<Integer> search(String p, String text) {
        int m = p.length(), n = text.length();
        if (m > 32) {
            throw new IllegalArgumentException("search pattern length must < 32");
        }
        if (m == 0) {
            return Collections.emptyList();
        }
        Map<Character, Integer> B = new HashMap<>();
        for (int j = 0; j < m; j++) {
            char c = p.charAt(j);
            B.put(c, B.getOrDefault(c, 0) | 1 << j);
        }

        int D = 0, mask = 1 << m - 1;
        List<Integer> idx = new ArrayList<>();
        for (int pos = 0; pos < n; pos++) {
            D = (D << 1 | 1) & B.getOrDefault(text.charAt(pos), 0);
            if ((D & mask) != 0) {
                idx.add(pos - m + 1);
            }
        }
        return idx;
    }

    public static void main(String[] args) {
        Utils.pprint("", "", new ShiftAnd());
        Utils.pprint("a", "a", new ShiftAnd());
        Utils.pprint("a", "", new ShiftAnd());
        Utils.pprint("", "a", new ShiftAnd());
        Utils.pprint("a", "ba", new ShiftAnd());
        Utils.pprint("announce", "annual_announce", new ShiftAnd());
        Utils.pprint("ATATA", "AGATACGATATATAC", new ShiftAnd());
    }
}
