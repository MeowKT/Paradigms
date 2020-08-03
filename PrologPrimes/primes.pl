get_sieve(N, 2) :- 0 is N mod 2, !.
get_sieve(N, R) :- sieve(N, R).

next(Z, Z) :- not composite(Z), !.
next(Z, Y) :- Z1 is Z + 2, next(Z1, Y).

set_sieve(N, R) :- composite(N), !.
set_sieve(N, R) :-
	assert(composite(N)),
	assert(sieve(N, R)).

set_composites(N, D, Dx) :-
	D < N,
	set_sieve(D, Dx),
	D1 is D + Dx,
	set_composites(N, D1, Dx).

build_sieve(N, R) :-
	R < N,
	SqR is R * R,
	assert(sieve(R, R)),
	not(set_composites(N, SqR, R)),
	R1 is R + 2,
	next(R1, R2),
	build_sieve(N, R2).

init(N) :-
	build_sieve(N, 3).

composite(1) :- !.
composite(N) :-
	0 is N mod 2,
	N > 2.

prime(2) :- !.
prime(N) :-
	not(composite(N)).

mul([], 1) :- !.
mul([H | T], Res) :- mul(T, Res2), Res is Res2 * H.
is_sorted([H]).
is_sorted([H, H1 | T]) :- H =< H1, is_sorted([H1 | T]).
is_prime([]).
is_prime([H | T]) :- prime(H), is_prime(T).

prime_divisors(1, []) :- !.
prime_divisors(N, [H | T]) :-
  number(N),
 	get_sieve(N, H),
 	NA is (N / H),
 	prime_divisors(NA, T),
 	!.
prime_divisors(N, X) :-
	not number(N),
	is_sorted(X),
	is_prime(X),
	mul(X, N).

merge(X, [], X) :- !.
merge([H | T1], [H | T2], [H | Res]) :- merge(T1, T2, Res), !.
merge([H1 | T1], [H2 | T2], [H1 | Res]) :- H1 < H2, merge(T1, [H2 | T2], Res), !.
merge(L, R, Res) :- merge(R, L, Res).

lcm(A, B, Res) :- prime_divisors(A, Res1), prime_divisors(B, Res2), merge(Res1, Res2, Ans), mul(Ans, Res).