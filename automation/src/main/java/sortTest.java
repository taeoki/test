import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class sortTest {
	public static void main(String[] args) {
		LinkedList<video> list = new LinkedList<video>();
		
		list.add(new video("t1","u1","미국"));
		list.add(new video("a1","u1","베트남"));
		list.add(new video("t2","u2","미국"));
		list.add(new video("c1","u1","중국"));
		list.add(new video("a2","u2","베트남"));
		list.add(new video("c2","u2","중국"));
		list.add(new video("v1","u1","한국"));
		list.add(new video("t3","u3","미국"));
		
		System.out.println("정렬 전");
		for (video item:list)
		{
			item.output();
		}
		
		Collections.sort(list, new Comparator<video>() {
			
			public int compare(video o1, video o2)
			{
				return o1.country.compareTo(o2.country);
			}
		});
		System.out.println("----정렬후-----");
		for (video item2:list)
		{
			item2.output();
		}
		
	}
}

class video {
	String title;
	String url;
	String country;
	
	video(String title, String url, String country)
	{
		this.title = title;
		this.url = url;
		this.country = country;
	}
	void output() {
		System.out.println("-"+title+"-"+url+"-"+country);
	}
}
