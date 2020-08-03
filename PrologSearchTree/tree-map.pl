split(nil, X, nil, nil).
split(tree((K, V, P), L, R), X, T1, tree((K, V, P), T3, R)) :- X < K, split(L, X, T1, T3).
split(tree((K, V, P), L, R), X, tree((K, V, P), L, T3), T2) :- X >= K, split(R, X, T3, T2).

merge(R, nil, R).
merge(L, L, nil).
merge(tree((LK, LV, LP), LL, NR), tree((LK, LV, LP), LL, LR), tree((RK, RV, RP), RL, RR)) :-
		LP > RP,
		merge(NR, LR, tree((RK, RV, RP), RL, RR)).
merge(tree((RK, RV, RP), NL, RR), tree((LK, LV, LP), LL, LR), tree((RK, RV, RP), RL, RR)) :-
        LP =< RP,
		merge(NL, tree((LK, LV, LP), LL, LR), RL).

search(nil, _, _) :- false.
search(Root, K, V) :-
		K2 is K - 1,
		split(Root, K2, T1, T2),
		split(T2, K, tree((K, V, P3), L3, R3), T4),
		merge(Tmp, T1, tree((K, V, P3), L3, R3)),
		merge(Res, Tmp, T4).

map_get(T, K, V) :- search(T, K, V).

insert(nil, (K, V, P), tree((K, V, P), nil, nil)).
insert(tree((K, V, P), L, R), (NK, NV, NP), Res) :-
		split(tree((K, V, P), L, R), NK, T1, T2),
		merge(Tmp, T1, tree((NK, NV, NP), nil, nil)),
		merge(Res, Tmp, T2).

map_remove(nil, _, nil).
map_remove(Root, K, Res) :-
		K2 is K - 1,
		split(Root, K2, T1, T2),
		split(T2, K, _, T3),
		merge(Res, T1, T3).

replace(nil, K, V, nil).
replace(Root, K, NV, Res) :-
		K2 is K - 1,
		split(Root, K2, T1, T2),
		split(T2, K, tree((K, V, P3), L3, R3), T4),
		merge(Tmp, T1, tree((K, NV, P3), L3, R3)),
		merge(Res, Tmp, T4).

map_put(T, K, V, R) :- search(T, K, _), replace(T, K, V, R), !.
map_put(T, K, V, R) :- rand_int(2147483647, P), insert(T, (K, V, P), R).

map_build([], nil).
map_build([(K, V) | L], T) :- map_build(L, NT), map_put(NT, K, V, T).

map_submapSize(nil, FROM, TO, 0) :- !.
map_submapSize(tree((K, _, _), L, R), FROM, TO, SZ) :- K < FROM, map_submapSize(R, FROM, TO, SZ), !.
map_submapSize(tree((K, _, _), L, R), FROM, TO, SZ) :- K >= TO, map_submapSize(L, FROM, TO, SZ), !.
map_submapSize(tree((_, _, _), L, R), FROM, TO, SZ) :- map_submapSize(L, FROM, TO, RL), map_submapSize(R, FROM, TO, RR), SZ is RL + RR + 1, !.

