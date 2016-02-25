#include <iostream>
#include <string>
#include <vector>
#include <stdexcept>
#include <sstream>
#include <iomanip>

using namespace std;

class Hora {
public:
	Hora(){};

	Hora(int hora, int minuto, int segundo) {
		_hora = hora;
		_minuto = minuto;
		_segundo = segundo;
		if (0 < _hora || _hora > 23 || 0 < _minuto || _minuto > 59 || 0 < _segundo || _segundo > 59) {
				throw invalid_argument("Error");
		}
	}

	bool operator <(const Hora &p2) const {
		if (_hora < p2._hora) {
			return true;
		}
		else if (_hora > p2._hora) {
			return false;
		}
		else {
			// Las horas coinciden.
			if (_minuto < p2._minuto) {
				return true;
			}
			else if (_minuto > p2._minuto) {
				return false;
			}
			else {
				// Coinciden horas y minutos
				if (_segundo < p2._segundo) {
					return true;
				}
				else {
					// Los segundos de esta hora son posteriores o iguales
					return false;
				}
			}
		}
	}
	
	friend ostream& operator << (ostream &o, const Hora &p) {
		cout << setfill('0');
		o << setw(2) << p._hora << ":" << setw(2) << p._minuto << ":" << setw(2) << p._segundo;
		return o;
	}
	friend istream& operator >> (istream &o, Hora &p) {
		o >> p._hora;
		o.ignore();
		o >> p._minuto;
		o.ignore();
		o >> p._segundo;
		if (0 > p._hora || p._hora > 23 || 0 > p._minuto || p._minuto > 59 || 0 > p._segundo || p._segundo > 59) {
			throw invalid_argument("Error");
		}
		return o;
	}

private:
	int _hora;
	int _minuto;
	int _segundo;
};


void buscar(const vector <Hora> lista, const Hora buscado, int ini, int fin, int & pos) {
	if (ini <= fin) {
		int mitad = (ini + fin) / 2;
		if (buscado < lista[mitad]) {
			buscar(lista, buscado, ini, mitad - 1, pos);
		}
		else if (lista[mitad] < buscado) {
			buscar(lista, buscado, mitad + 1, fin, pos);
		}
		else { 
			pos = mitad;
		}
	}
	else {
		pos = ini;
	} 
}

int main() {
	int numFechas;
	int casos;
	vector <Hora> horarios;
	Hora aux;
	do {		
		cin >> numFechas;
		cin >> casos;
		//Leemos el horario de trenes
		for (int i = 0; i < numFechas; i++) {	
			cin >> aux;
			horarios.push_back(aux);
		}
		// Comprobamos horarios
		for (int i = 0; i < casos; i++) {
			try {
				cin >> aux;
				// buscamos la hora
				int p;
				buscar(horarios, aux, 0, int(horarios.size()) - 1, p);
				if (p == int(horarios.size())) {
					cout << "NO" << endl;
				} else {
					cout << horarios[p] << endl;
				}
			}
			catch (invalid_argument e) {
				cout << "ERROR" << endl;
			}
		}
		if (casos != 0 || numFechas != 0) {
			cout << "---" << endl;
		}
		horarios.clear();
	} while (casos != 0 || numFechas != 0);
	return 0;
}
