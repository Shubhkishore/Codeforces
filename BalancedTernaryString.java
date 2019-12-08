import java.util.Scanner;

/**
 * @author johnny16
 *
 */
public class BalancedTernaryString {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int n, i, zeros = 0, ones = 0, twos = 0;
		String s;
		n = in.nextInt();
		in.next();
		s = in.nextLine();
		for (char c : s.toCharArray()) {
			if (c == '0')
				zeros++;
			else if (c == '1')
				ones++;
			else
				twos++;
		}
		if (ones == zeros && ones == twos) {
			System.out.println(s);
			return;
		}
		int excessZeros = zeros - n / 3;
		int excessOnes = ones - n / 3;
		int excessTwos = twos - n / 3;
		int deficitZero = -1 * excessZeros;
		int deficitOnes = -1 * excessOnes;
		int deficitTwos = -1 * excessTwos;

		StringBuilder st = new StringBuilder(s);
		if (excessZeros > 0) {
			int count = n / 3;
			for (i = 0; i < n; i++) {
				char c = st.charAt(i);
				if (count == 0 && excessZeros>0) {
					if (c == '0') {
						if (deficitOnes > 0) {
							st.setCharAt(i, '1' );
							deficitOnes--;
							excessZeros--;
						}
						else if(deficitOnes==0 && deficitTwos>0) {
							st.setCharAt(i, '2');
							deficitTwos--;
							excessZeros--;
						}
					}
				}
				if (c == '0')
					count--;
			}
		}
	}

}
