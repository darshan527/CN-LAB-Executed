import java.util.*;

public class LeakyBucket {
	static int bucket_size, time_interval = 0;

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		LeakyBucket lb = new LeakyBucket();

		System.out.print("Enter the bucket size = ");
		bucket_size = in.nextInt();
		System.out.println("Enter Output rate");
		int psend = in.nextInt();
		int temp = psend;
		int rem_bucket_size = bucket_size;

		try {
			while (time_interval < 10) {
				System.out.println("\n\n\nAt time interval = " + (time_interval + 1));

				System.out.print("\nBucket size = " + rem_bucket_size);

				int packet_size = lb.generator();
				System.out.print("\nPackets recieved = " + packet_size);

				if (packet_size > rem_bucket_size) {
					System.out.print("\nPackets dropped = " + (packet_size - rem_bucket_size));
					rem_bucket_size = 0;
					System.out.print("\nRemaining bucket size = " + rem_bucket_size);
				} else {
					System.out.print("\nPackets dropped = 0");
					rem_bucket_size -= packet_size;
					System.out.print("\nRemaining bucket size = " + rem_bucket_size);
				}

				System.out.print("\nPackets available = " + (bucket_size - rem_bucket_size));
				if ((bucket_size - rem_bucket_size) < psend)
					psend = (bucket_size - rem_bucket_size);
				System.out.print("\nPackets sent = " + psend);
				rem_bucket_size += psend;
				System.out.print("\nRemaining bucket size = " + rem_bucket_size);
				System.out.print("\nPackets available = " + (bucket_size - rem_bucket_size));

				time_interval++;
				Thread.sleep(1000);
				psend = temp;
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected int generator() {
		Random r = new Random();
		return r.nextInt(bucket_size) + 1;
	}

}
