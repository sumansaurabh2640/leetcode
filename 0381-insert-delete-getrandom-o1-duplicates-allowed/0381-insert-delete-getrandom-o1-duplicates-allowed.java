// class RandomizedCollection {

//     public RandomizedCollection() {
        
//     }
    
//     public boolean insert(int val) {
        
//     }
    
//     public boolean remove(int val) {
        
//     }
    
//     public int getRandom() {
        
//     }
// }

// /**
//  * Your RandomizedCollection object will be instantiated and called as such:
//  * RandomizedCollection obj = new RandomizedCollection();
//  * boolean param_1 = obj.insert(val);
//  * boolean param_2 = obj.remove(val);
//  * int param_3 = obj.getRandom();
//  */

import java.util.*;

class RandomizedCollection {
    private List<Integer> list;
    private Map<Integer, Set<Integer>> map;
    private Random random;

    public RandomizedCollection() {
        list = new ArrayList<>();
        map = new HashMap<>();
        random = new Random();
    }

    public boolean insert(int val) {
        boolean isNew = !map.containsKey(val);

        map.putIfAbsent(val, new HashSet<>());
        map.get(val).add(list.size());
        list.add(val);

        return isNew;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val) || map.get(val).isEmpty()) {
            return false;
        }

        Set<Integer> indices = map.get(val);
        int removeIndex = indices.iterator().next();
        indices.remove(removeIndex);

        int lastIndex = list.size() - 1;
        int lastValue = list.get(lastIndex);

        if (removeIndex != lastIndex) {
            list.set(removeIndex, lastValue);

            Set<Integer> lastIndices = map.get(lastValue);
            lastIndices.remove(lastIndex);
            lastIndices.add(removeIndex);
        }

        list.remove(lastIndex);

        if (indices.isEmpty()) {
            map.remove(val);
        }

        return true;
    }

    public int getRandom() {
        return list.get(random.nextInt(list.size()));
    }
}