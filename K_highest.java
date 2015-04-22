public class K_highest {
	
	
	public static void main(String[] args){
		int[] array = {1,2,2,3,3,3,4,4,4,4,5,5,5,5,5};
		System.out.println(
				khigh(array, 2)
				);
	}
	
	
	public static ArrayList<Integer> khigh(int[] array, int k){
		ArrayList<Integer> res = new ArrayList<Integer>();
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		Comparator<Map.Entry<Integer, Integer>> comp = new Comparator<Map.Entry<Integer, Integer>>(){
			public int compare(Map.Entry<Integer, Integer> e1, Map.Entry<Integer, Integer> e2){
				if(e1.getValue() > e2.getValue()) return -1;
				if(e1.getValue() < e2.getValue()) return 1;
				return 0;
			}
		};
		
		for(int i = 0; i < array.length ; i++){
			if(map.containsKey(array[i])) {
				int temp = map.get(array[i]);
				temp++;
				map.put(array[i],temp);
			}else
			
			map.put(array[i], 1);
		}
		
		List<Map.Entry<Integer, Integer>> list = new ArrayList<Map.Entry<Integer, Integer>>(map.entrySet());
		
		Collections.sort(list, comp);// O(NlogN)
		
		for(int i = 0; i < k; i++){
			res.add(list.get(i).getValue());
		}
		return res;
	}
}