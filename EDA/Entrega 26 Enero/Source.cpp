#include <iostream>
#include <climits>

using namespace std;

void vueltaAtras(int S[100][100], int B[100][100], const int & tam, int & sol, int & solMax, int col, bool usadas[100], int maxSatis, int minSatis);

int main() {

	int S[100][100];
	int B[100][100];

	int tam, casos;
	int solMax, col, sol, maxSatis, minSatis;
	bool usadas[100];

	cin >> casos;

	for (int i = 0; i < casos; i++){

		solMax = 0;
		col = 0;
		sol = 0;
		maxSatis = INT_MAX;
		minSatis = 0;

		cin >> tam;

		

		for (int j = 0; j < tam; j++){  
			for (int k = 0; k < tam; k++){
				cin >> S[j][k];
			}
		}

		for (int j = 0; j < tam; j++){
			for (int k = 0; k < tam; k++){
				cin >> B[j][k];
			}
		}

		for (int j = 0; j < tam; j++){
			usadas[j] = false;
		}

		

		vueltaAtras(S, B, tam, sol, solMax, col, usadas, maxSatis, minSatis);
		
		if (solMax == 0) {
			cout << "Sin solucion factible" << endl;
		}
		else {
			cout << solMax << endl;
		}

	}

	return 0;
}

void vueltaAtras(int S[100][100], int B[100][100], const int & tam, int & sol, int & solMax, int col, bool usadas[100], int maxSatis, int minSatis ) {
	for (int i = 0; i < tam; i++){

		
	//Solucion
		if (col == tam - 1){
			if (!usadas[i] && S[i][col] < maxSatis && S[i][col] > minSatis)  {
				sol += B[i][col];
				if (sol > solMax) {
					solMax = sol;
				}
				sol -= B[i][col];
			}
		}

		else { 
			if (!usadas[i] && S[i][col] < maxSatis && S[i][col] > minSatis ) {

				usadas[i] = true;
				sol += B[i][col];
				
				vueltaAtras(S, B, tam, sol, solMax, col + 1, usadas, (S[i][col] * 2 < maxSatis) ? S[i][col] * 2 : maxSatis, (S[i][col] / 2 > minSatis) ? S[i][col] / 2 : minSatis);
				sol = sol - B[i][col];
				usadas[i] = false;

			}

		}

	}
}
