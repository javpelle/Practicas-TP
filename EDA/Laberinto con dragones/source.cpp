#include <iostream>
#include <stdexcept>
#include <queue>
#include <list>
#include <assert.h>
#include <iostream>
#include <algorithm>
#include <string>


using namespace std;

template <class T>
class Arbin {

public:

	/** Constructor; operacion ArbolVacio */
	Arbin() : _ra(nullptr) {
	}


	/** Constructor; operacion Cons */
	Arbin(const Arbin &iz, const T &elem, const Arbin &dr) :
		_ra(new Nodo(iz._ra, elem, dr._ra)) {
		_ra->addRef();
	}

	Arbin(const T &elem) :
		_ra(new Nodo(nullptr, elem, nullptr)) {
		_ra->addRef();
	}

	/** Destructor; elimina la estructura jer·rquica de nodos. */
	~Arbin() {
		libera();
		_ra = nullptr;
	}

	/**
	Devuelve el elemento almacenado en la raiz

	raiz(Cons(iz, elem, dr)) = elem
	error raiz(ArbolVacio)
	@return Elemento en la raÌz.
	*/
	const T &raiz() const {
		if (esVacio())
			throw std::domain_error("arbol vacio");
		return _ra->_elem;
	}

	/**
	Devuelve un ·rbol copia del ·rbol izquierdo.
	Es una operaciÛn parcial (falla con el ·rbol vacÌo).

	hijoIz(Cons(iz, elem, dr)) = iz
	error hijoIz(ArbolVacio)
	*/
	Arbin hijoIz() const {
		if (esVacio())
			throw std::domain_error("arbol vacio");

		return Arbin(_ra->_iz);
	}

	/**
	Devuelve un ·rbol copia del ·rbol derecho.
	Es una operaciÛn parcial (falla con el ·rbol vacÌo).

	hijoDr(Cons(iz, elem, dr)) = dr
	error hijoDr(ArbolVacio)
	*/
	Arbin hijoDr() const {
		if (esVacio())
			throw std::domain_error("arbol vacio");

		return Arbin(_ra->_dr);
	}

	/**
	OperaciÛn observadora que devuelve si el ·rbol
	es vacÌo (no contiene elementos) o no.

	esVacio(ArbolVacio) = true
	esVacio(Cons(iz, elem, dr)) = false
	*/
	bool esVacio() const {
		return _ra == nullptr;
	}

	// //
	// RECORRIDOS SOBRE EL ¡RBOL
	// //

	std::list<T> preorden() const {
		std::list<T> ret;
		preordenAcu(_ra, ret);
		return ret;
	}

	std::list<T> inorden() const {
		std::list<T> ret;
		inordenAcu(_ra, ret);
		return ret;
	}

	std::list<T> postorden() const {
		std::list<T> ret;
		postordenAcu(_ra, ret);
		return ret;
	}

	std::list<T> niveles() const {

		if (esVacio())
			return std::list<T>();

		std::list<T> ret;
		std::queue<Nodo*> porProcesar;
		porProcesar.push(_ra);

		while (!porProcesar.empty()) {
			Nodo *visita = porProcesar.front();
			porProcesar.pop();
			ret.push(visita->_elem);
			if (visita->_iz)
				porProcesar.push(visita->_iz);
			if (visita->_dr)
				porProcesar.push(visita->_dr);
		}

		return ret;
	}


	// //
	// OTRAS OPERACIONES OBSERVADORAS
	// //



	// //
	// M…TODOS DE "FONTANERÕA" DE C++ QUE HACEN VERS¡TIL
	// A LA CLASE
	// //

	/** Constructor copia */
	Arbin(Arbin<T> const& other) : _ra(nullptr) {
		copia(other);
	}

	/** Operador de asignaciÛn */
	Arbin<T> &operator=(Arbin<T> const& other) {
		if (this != &other) {
			libera();
			copia(other);
		}
		return *this;
	}

	/** Operador de comparaciÛn. */
	bool operator==(Arbin<T> const& rhs) const {
		return comparaAux(_ra, rhs._ra);
	}

	bool operator!=(Arbin<T> const& rhs) const {
		return !(*this == rhs);
	}

private:

	/**
	Clase nodo que almacena internamente el elemento (de tipo T),
	y los punteros al hijo izquierdo y al hijo derecho, asÌ
	como el n˙mero de referencias que hay.
	*/
	class Nodo {
	public:
		Nodo() : _iz(nullptr), _dr(nullptr), _numRefs(0) {}
		Nodo(Nodo *iz, T const& elem, Nodo *dr) :
			_elem(elem), _iz(iz), _dr(dr), _numRefs(0) {
			if (_iz != nullptr)
				_iz->addRef();
			if (_dr != nullptr)
				_dr->addRef();
		}

		void addRef() { assert(_numRefs >= 0); _numRefs++; }
		void remRef() { assert(_numRefs > 0); _numRefs--; }

		T _elem;
		Nodo *_iz;
		Nodo *_dr;

		int _numRefs;
	};

	/**
	Constructor protegido que crea un ·rbol
	a partir de una estructura jer·rquica existente.
	Esa estructura jer·rquica SE COMPARTE, por lo que
	se aÒade la referencia.
	Se utiliza en hijoIz e hijoDr.
	*/
	Arbin(Nodo *raiz) : _ra(raiz) {
		if (_ra != nullptr)
			_ra->addRef();
	}

	void libera() {
		libera(_ra);
	}

	void copia(Arbin const& other) {
		assert(this != &other);
		_ra = other._ra;
		if (_ra != nullptr)
			_ra->addRef();
	}

	// //
	// M…TODOS AUXILIARES PARA LOS RECORRIDOS
	// //

	static void preordenAcu(Nodo *ra, std::list<T> &acu) {
		if (ra == nullptr)
			return;

		acu.push_back(ra->_elem);
		preordenAcu(ra->_iz, acu);
		preordenAcu(ra->_dr, acu);
	}

	static void inordenAcu(Nodo *ra, std::list<T> &acu) {
		if (ra == nullptr)
			return;

		inordenAcu(ra->_iz, acu);
		acu.push_back(ra->_elem);
		inordenAcu(ra->_dr, acu);
	}

	static void postordenAcu(Nodo *ra, std::list<T> &acu) {
		if (ra == nullptr)
			return;

		postordenAcu(ra->_iz, acu);
		postordenAcu(ra->_dr, acu);
		acu.push_back(ra->_elem);
	}

	// //
	// M…TODOS AUXILIARES (RECURSIVOS) DE OTRAS OPERACIONES
	// OBSERVADORAS
	// //

	/**
	Elimina todos los nodos de una estructura arbÛrea
	que comienza con el puntero ra.
	Se admite que el nodo sea nullptr (no habr· nada que
	liberar).
	*/
	static void libera(Nodo *ra) {
		if (ra != nullptr) {
			ra->remRef();
			if (ra->_numRefs == 0) {
				libera(ra->_iz);
				libera(ra->_dr);
				delete ra;
			}
		}
	}

	/**
	Compara dos estructuras jer·rquicas de nodos,
	dadas sus raices (que pueden ser nullptr).
	*/
	static bool comparaAux(Nodo *r1, Nodo *r2) {
		if (r1 == r2)
			return true;
		else if ((r1 == nullptr) || (r2 == nullptr))
			// En el if anterior nos aseguramos de
			// que r1 != r2. Si uno es nullptr, el
			// otro entonces no lo ser·, luego
			// son distintos.
			return false;
		else {
			return (r1->_elem == r2->_elem) &&
				comparaAux(r1->_iz, r2->_iz) &&
				comparaAux(r1->_dr, r2->_dr);
		}
	}


	/**
	Puntero a la raÌz de la estructura jer·rquica
	de nodos.
	*/
	Nodo *_ra;

};




template <class T>
Arbin<T>	leerArbol(T const&	vacio) {
	T raiz;	cin >> raiz;
	if (raiz == vacio)	return Arbin<T>();
	Arbin<T> a1 = leerArbol(vacio);
	Arbin<T> a2 = leerArbol(vacio);
	return Arbin<T>(a1, raiz, a2);
}

void recursivo(const Arbin<string> & arbol, int & dragonesOp, int dragones, string & puerta) {
	if (!arbol.esVacio()) {
		if (arbol.hijoDr().esVacio() && arbol.hijoIz().esVacio()) {
			// hemos llegado a una hoja
			if (dragones < dragonesOp) {
				dragonesOp = dragones;
				puerta = arbol.raiz();
			}
		}
		else {
			if (arbol.raiz() == "Dragon") {
				dragones++;
			}
			recursivo(arbol.hijoIz(), dragonesOp, dragones, puerta);
			recursivo(arbol.hijoDr(), dragonesOp, dragones, puerta);
		}
	}

}

void resuelveCaso() {
	Arbin<string> arbol;
	string puntos = "...";
	arbol = leerArbol(puntos);
	string puerta;
	int dragonesOp = 9999999;
	int dragon = 0;
	recursivo(arbol, dragonesOp, dragon, puerta);
	cout << puerta << " " << dragonesOp << endl;
}
int main() {
	int casos;
	cin >> casos;
	for (int i = 0; i < casos; i++) {
		resuelveCaso();
	}
	return 0;
}