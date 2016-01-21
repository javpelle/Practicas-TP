#include <iostream>

using namespace std;

void vueltaAtrás(int S[100][100], int B[100][100], int tam, int & sol, int & solMax, int & col, bool usadas[100], int & maxSatis, int & minSatis, int satisMaxTemporal, int satisMinTemporal);

int main() {

	int S[100][100];
	int B[100][100];

	int tam, casos;
	int solMax = 0;
	int col = 0;
	int sol = 0;
	int satisMaxTemporal;
	int satisMinTemporal;
	int maxSatis, minSatis;
	bool usadas[100];

	cin >> casos;

	for (int i = 0; i < casos; i++){
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

		

		vueltaAtrás(S, B, tam, sol, solMax, col, usadas, maxSatis, minSatis, satisMaxTemporal, satisMinTemporal);
		
		if (solMax == 0) {
			cout << "Sin solucion factible" << endl;
		}
		else {
			cout << solMax << endl;
		}

	}

	return 0;
}

void vueltaAtrás(int S[100][100], int B[100][100], int tam, int & sol, int & solMax, int & col, bool usadas[100], int & maxSatis, int & minSatis, int satisMaxTemporal, int satisMinTemporal  ) {
	for (int i = 0; i < tam; i++){

	//Preparar
		if (col == 0){
			maxSatis = S[i][col] * 2;
			minSatis = S[i][col] / 2;
		}
	//Solucion
		if (col == tam - 1){
			if (!usadas[i] && S[i][col] < maxSatis && S[i][col] > minSatis)  {
				sol += B[i][col];
				if (sol > solMax) {
					solMax = sol;
				}
			}
		}

		else { 
			if (!usadas[i] && S[i][col] < maxSatis && S[i][col] > minSatis ) {

				satisMaxTemporal = maxSatis;
				satisMinTemporal = minSatis;

				if (S[i][col] * 2 < maxSatis){ //Nuevo satisfacción máximo
					maxSatis = S[i][col] * 2;
				} 

				if (S[i][col] / 2 > minSatis){ //Nueva satisfacción minima
					minSatis = S[i][col];
				}
				
				usadas[i] = true;
				sol += B[i][col];
				col++;
				
				vueltaAtrás(S, B, tam, sol, solMax, col, usadas, maxSatis, minSatis, satisMaxTemporal, satisMinTemporal);
				col--;
				sol = sol - B[i][col];
				usadas[i] = false;
				maxSatis = satisMaxTemporal;
				minSatis = satisMinTemporal;

			}

		}

	}
}
