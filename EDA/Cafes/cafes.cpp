#include <iostream>
#include <vector>
#include <stdexcept>
#include <sstream>
#include <iomanip>
#include <string>
#include <algorithm>

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
	
	Hora(int segundos) {
		_hora = segundos / 3600;
		segundos = segundos % 3600;
		_minuto = segundos / 60;
		_segundo = segundos % 60;
	}
	
	int toSegundos() const{
		return _hora * 3600 + _minuto * 60 + _segundo;
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
	
	bool operator ==(const Hora &p2) const {
		return ((_hora==p2._hora)&&(_minuto==p2._minuto)&&(_segundo==p2._segundo));
	}
	
	Hora& operator + (const Hora &p2) {
		_segundo += p2._segundo;
		if (_segundo >= 60) {
			_segundo -= 60;
			_minuto++;
		}
		_minuto += p2._minuto;
		if (_minuto >= 60) {
			_minuto -= 60;
			_hora++;
		}
		_hora += p2._hora;
		if (_hora >= 24) {
			throw invalid_argument("ERROR");
		}		
		return *this;
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


int main() {
	int numTrabajos;
	vector <Hora> horarios;
	Hora aux;
	do {	
		horarios.clear();	
		std::cin >> numTrabajos;
		//Leemos el horario de trabajos
		for (int i = 0; i < numTrabajos; i++) {
			try {	
				std::cin >> aux;
				horarios.push_back(aux);
			} catch (invalid_argument e) {
			
			}
		}
		// Ordenamos el vector
		sort (horarios.begin(), horarios.end());
		
		int segunditos = 0, auxTrab, posicion;
		for(int i = 0;i < numTrabajos - 1; i++) {
			auxTrab = horarios[i + 1].toSegundos() - horarios[i].toSegundos();
			if (auxTrab > segunditos) {
				segunditos = auxTrab;
				posicion = i;
			}
		}
		if (numTrabajos != 0) {
			Hora segundazos(segunditos);
			cout << horarios[posicion] << " " << segundazos << endl;
		}		
		
		horarios.clear();
	} while (numTrabajos != 0);
	return 0;
}