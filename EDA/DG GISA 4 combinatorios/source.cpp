#include <iostream>

using namespace std;

void combinatorio();

long int combinatorio (const int &m, int n) {
	if (n == 0) {
		// caso base
		return 1;
	} else {
		long int combinatorioAnt = combinatorio(m, n - 1)%1000007;
		return (combinatorioAnt * (m + 1 - n)/n);
	}
}

int main() {
	int numCasos;
	cin >> numCasos;
	for (int c = 0; c < numCasos; c++) {
		int m, n;
		cin >> m >> n;
		if (m - n < n) {
			n = m - n;
		}
		cout << (combinatorio(m,n)) << endl;
	}
	return 0;
}
