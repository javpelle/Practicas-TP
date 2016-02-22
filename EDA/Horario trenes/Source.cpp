#include <iostream>
#include <string>
#include <vector>
#include <stdexcept>
#include <sstream>

using namespace std;

namespace patch
{
	template < typename T > std::string to_string(const T& n)
	{
		std::ostringstream stm;
		stm << n;
		return stm.str();
	}
}

class Hora {
public:
	Hora(int hora, int minuto, int segundo) {
		_hora = hora;
		_minuto = minuto;
		_segundo = segundo;
	}

		string toString() const {
			// devuelve la hora
			string horaString = "";
			if (_hora / 10 == 0) {
				horaString = horaString + "0" + patch::to_string(_hora) + ":";
			} else {
				horaString += patch::to_string(_hora) + ":";
			}
			if (_minuto / 10 == 0) {
				horaString = horaString + "0" + patch::to_string(_minuto) + ":";
			}
			else {
				horaString += patch::to_string(_minuto) + ":";
			}
			if (_segundo / 10 == 0) {
				horaString = horaString + "0" + patch::to_string(_segundo);
			}
			else {
				horaString += patch::to_string(_segundo);
			}
			return horaString;			
		}

		friend bool operator <(const Hora &p1, const Hora &p2) {
			if (p1._hora < p2._hora) {
				return true;
			}
			else if (p1._hora > p2._hora) {
				return false;
			}
			else {
				// Las horas coinciden.
				if (p1._minuto < p2._minuto) {
					return true;
				}
				else if (p1._minuto > p2._minuto) {
					return false;
				}
				else {
					// Coinciden horas y minutos
					if (p1._segundo < p2._segundo) {
						return true;
					}
					else {
						// Los segundos de esta hora son posteriores o iguales
						return false;
					}
				}
			}
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
	do {		
		cin >> numFechas;
		cin >> casos;
		vector <Hora> horarios;
		//Leemos el horario de trenes
		for (int i = 0; i < numFechas; i++) {
			int horaAux, minutoAux, segundoAux;
			cin >> horaAux;
			cin.ignore();
			cin >> minutoAux;
			cin.ignore();
			cin >> segundoAux;
			horarios.push_back(Hora(horaAux, minutoAux, segundoAux));
		}
		// Comprobamos horarios
		for (int i = 0; i < casos; i++) {
			try {
				int horaAux, minutoAux, segundoAux;
				cin >> horaAux;
				cin.ignore();
				cin >> minutoAux;
				cin.ignore();
				cin >> segundoAux;
				if (horaAux < 0 || horaAux > 23 || minutoAux < 0 || minutoAux > 59 || segundoAux < 0 || segundoAux > 59) {
					throw (invalid_argument("ERROR"));
				}
				else {
					// buscamos la hora
					Hora aux(horaAux, minutoAux, segundoAux);
					int p;
					buscar(horarios, aux, 0, int(horarios.size()) - 1, p);
					if (p == int(horarios.size())) {
						cout << "NO" << endl;
					} else {
						cout << horarios[p].toString() << endl;
					}
				}
			}
			catch (invalid_argument e) {
				cout << "ERROR" << endl;
			}
		}
		if (casos != 0 || numFechas != 0) {
			cout << "---" << endl;
		}
	} while (casos != 0 || numFechas != 0);
	return 0;
}
