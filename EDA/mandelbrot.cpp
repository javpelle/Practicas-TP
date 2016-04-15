#include <iostream>
#include <cmath>

using namespace std;

template <class T>
class Mandelbrot {
private:
	T x;
	T y;

public:
	Mandelbrot(T x, T y) {
		this->x = x;
		this->y = y;
	}

	Mandelbrot<T> & operator +(const Mandelbrot<T>  p2) const {
		return new Mandelbrot<T>(x + p2.x, y + p2.y);
	}

	Mandelbrot<T> & operator *(const Mandelbrot<T>  p2) const {
		return new Mandelbrot<T>(x*p2.x - y*p2.y, x * p2.y + p2.x * y);
	}

	T mod() {
		return sqrt(x ^ 2 + y ^ 2);
	}
};



bool comprobar(const Mandelbrot<double> complex, int iteraciones) {
	Mandelbrot<double> anterior = Mandelbrot<double>(0, 0);
	Mandelbrot<double> result = complex;
	for (int i = 0; i < iteraciones; i++) {
		result = anterior * anterior;
		result = result + complex;
		anterior = complex;
	}
	double modulo = result.mod();
	if (modulo > 2) {
		return false;
	}
	else {
		return true;
	}

}

int main() {
	int numCasos, iteraciones;
	double x, y;
	cin >> numCasos;
	for (int i = 0; i < numCasos; i++) {
		cin >> x >> y >> iteraciones;
		Mandelbrot<double> num = Mandelbrot<double>(x, y);
		if (comprobar(num, iteraciones)) {
			cout << "SI" << endl;
		}
		else {
			cout << "NO" << endl;
		}
	}
	return 0;
}
