#include <iostream>
#include <string>
#include <vector>
#include <stdexcept>
#include <sstream>
#include <iomanip>

using namespace std;

template <class b>
class Polinomio {
private:
	b monomios[13];
	int grado;
public:
	Polinomio(){};
	
	void leer() {
		cin >> grado;
		for (int i = 0; i <= grado; i++) {
			cin >> monomios[i];
		}
	}

	b resultado(const int & valor) {
		b sol = 0;
		int exponencial = 1;
		for (int i = grado; i >= 0; i--) {
			sol = sol + monomios[i] * exponencial;
			exponencial = exponencial * valor;
		}
		return sol;
	}
};


int main() {
	Polinomio<int> eda;
	int valor;
	while (!cin) {
		eda.leer();
		do {
			cin >> valor;
			if (valor != 0) {
				cout << int(eda.resultado(valor)) << endl;
			}
		} while (valor != 0);
	}
	return 0;
}
