# tarea-1-modelos-computabilidad

Reconocedor de patrones en cadenas de texto usando autómatas


Ejemplo

Entrada
	babababababababa
	b
	computar
	a
	aa
	compu
	computador
	acabacab
	computadora
	_

Expresión:
	c.o.m.p.u.(t|a|d|o|r)*

Salida:
	computar
	compu
	computador
	computadora

