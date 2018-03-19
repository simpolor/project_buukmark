package project_buukmark;

public class TestPercent {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		double total = 352;
		double percent = 10;
		int count = (int)(((total * percent) / 100)+0.5);
		System.out.println(percent);
		System.out.println(count);
		
		for(int i=0; i<=total; i++) {
			if(i == count) {
				System.out.println(percent+"%");
				percent = percent + 10;
				count = (int)(((total * percent) / 100)+0.5);
			}
		}

	}

}
