import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Base {
	public static void Report(String name, int totalRatings, int totalCost, int[] players, String[] names) {
		System.out.println(name + " results:");
		System.out.println("Total ratings : " + totalRatings);
		System.out.println("Total cost : " + totalCost);
		System.out.println("Players:");
		for (int i = 0; i < names.length; i++) {
			if (players[i] != -1)
				System.out.println(i + 1 + "-" + names[players[i]]);
		}

	}

	public static void PrintMatrix(String[][] mat) {
		for (int t = 0; t < mat.length; t++) {
			for (int h = 0; h < mat[0].length; h++) {
				System.out.print(mat[t][h] + " ");
			}
			System.out.println();
		}
		System.out.println("-------------------------------------------------");
	}

	public static void PrintMatrix(int[][] mat) {
		for (int t = 0; t < mat.length; t++) {
			for (int h = 0; h < mat[0].length; h++) {
				System.out.print(mat[t][h] + " ");
			}
			System.out.println();
		}
		System.out.println("-------------------------------------------------");
	}

	public static int knapSack(int Cost, int Prices[], int Ratings[], int Size, Stack S, int[] Positions) {
		if (Size == 0 || Cost < Prices[Size - 1])
			return 0;

		if (!S.contains(Positions[Size - 1])) {
			int m = Ratings[Size - 1] + knapSack(Cost - Prices[Size - 1], Prices, Ratings, Size - 1, S, Positions);
			int k = knapSack(Cost, Prices, Ratings, Size - 1, S, Positions);
			if (m > k) {
				S.push(Positions[Size - 1]);
				return Ratings[Size - 1] + knapSack(Cost - Prices[Size - 1], Prices, Ratings, Size - 1, S, Positions);
			}

			else {
				S.pop();
				return knapSack(Cost, Prices, Ratings, Size - 1, S, Positions);
			}

		} else {

			return knapSack(Cost, Prices, Ratings, Size - 1, S, Positions);
		}

	}

	public static int prevSize = 99;

	public static int knapSack2(int Cost, int[] Prices, int[] Ratings, int[] Positions, int Size, String[] Names,
			Stack s) {

		if (Size == 0) {
			return 0;
		}

		// System.out.println(Names[Size - 1]);
		if (Cost >= Prices[Size - 1] && !s.contains(Positions[Size - 1])) {
			System.out.println(Names[Size - 1]);
			return Ratings[Size - 1]
					+ knapSack2(Cost - Prices[Size - 1], Prices, Ratings, Positions, Size - 1, Names, s);
		}


		return knapSack2(Cost, Prices, Ratings, Positions, Size - 1, Names, s);
	}

	public static boolean contains(final int[] array, final int v) {

		boolean result = false;

		for (int i : array) {
			if (i == v) {
				result = true;
				break;
			}
		}

		return result;
	}

	public static void greedyApproach(int X, int N, int K, int[] mainPrices, int[] mainRatings, int[] mainPositions,
			String[] mainNames) {
		int[] greedyPrices = new int[N * K];
		int[] greedyRatings = new int[N * K];
		int[] greedyPositions = new int[N * K];
		String[] greedyNames = new String[N * K];
		int max = 0;
		int maxIndex = 0;

		for (int i = 0; i < greedyPrices.length; i++) {
			for (int j = 0; j < greedyPrices.length; j++) {
				if (mainRatings[j] > max) {
					max = mainRatings[j];
					maxIndex = j;
				}
			}
			greedyPrices[i] = mainPrices[maxIndex];
			greedyRatings[i] = mainRatings[maxIndex];
			greedyPositions[i] = mainPositions[maxIndex];
			greedyNames[i] = mainNames[maxIndex];
			mainRatings[maxIndex] = 0;
			max = 0;
		}

		int greedyRating = 0;
		int remainMoney = X;
		int greedyPosition[] = new int[N * K];
		int greedyIndexes[] = new int[N * K];
		for (int i = 0; i < greedyIndexes.length; i++)
			greedyIndexes[i] = -1;
		int greedyCount = 0;
		for (int i = 0; i < mainRatings.length; i++) {
			if (remainMoney < greedyPrices[i])
				continue;
			else {
				boolean positionFlag = true;
				for (int j = 0; j < greedyPosition.length; j++) {
					if (greedyPosition[j] == greedyPositions[i]) {
						positionFlag = false;
						break;
					}
				}
				if (positionFlag) {
					greedyRating = greedyRating + greedyRatings[i];
					remainMoney = remainMoney - greedyPrices[i];
					greedyPosition[i] = greedyPositions[i];
					greedyIndexes[greedyCount] = i;
					greedyCount++;
				} else
					continue;
			}
		}
		Report("Greedy Approach", greedyRating, (X - remainMoney), greedyIndexes, greedyNames);
	}

	public static void main(String[] args) throws IOException {

		File file = new File("input.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
		String st;
		boolean flag = true;
		String[] names;
		int[] positions;
		int[] ratings;
		int[] prices;
		int lineCount = 0;

		while ((st = br.readLine()) != null) {
			lineCount++;

		}

		br.close();
		br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
		st = "";

		names = new String[lineCount];
		positions = new int[lineCount];
		ratings = new int[lineCount];
		prices = new int[lineCount];
		lineCount = 0;

		while ((st = br.readLine()) != null) {
			if (flag) {
				st = st.substring(1);
				flag = false;
			}

			String[] line = st.split("	");
			names[lineCount] = line[0];
			positions[lineCount] = Integer.parseInt(line[1]);
			ratings[lineCount] = Integer.parseInt(line[2]);
			prices[lineCount] = Integer.parseInt(line[3]);
			lineCount++;

		}

		Scanner input = new Scanner(System.in);
		System.out.println("File is read.");
		System.out.print("Enter the amount to spend (X): ");
		int X = input.nextInt();
		System.out.print("Enter the number of the positions (N): ");
		int N = input.nextInt();
		System.out.print("Enter the number of the available players for each position (K): ");
		int K = input.nextInt();
		System.out.println("Processing...");

		int positionCount = 1;
		int playerCount = 0;
		int arraySearch = 0;
		int[] mainPrices = new int[N * K];
		int[] mainRatings = new int[N * K];
		int[] mainPositions = new int[N * K];
		String[] mainNames = new String[N * K];

		while (positionCount <= N) {
			for (int i = 0; i < K; i++) {
				mainPrices[playerCount] = prices[arraySearch];
				mainRatings[playerCount] = ratings[arraySearch];
				mainNames[playerCount] = names[arraySearch];
				mainPositions[playerCount] = positions[arraySearch];
				playerCount++;
				arraySearch++;
			}

			while (arraySearch != positions.length && positions[arraySearch] == positionCount) {
				arraySearch++;
			}
			positionCount++;
		}

//---------------------------------------------------------------------------------------------------------
		//greedyApproach(X, N, K, mainPrices, mainRatings, mainPositions, mainNames);
		System.out.println("-------------------");
//---------------------------------------------------------------------------------------------------------

		Stack s = new Stack(mainPositions.length);
		System.out.println(knapSack2(X, mainPrices, mainRatings, mainPositions, mainRatings.length, mainNames, s));
	}
}
