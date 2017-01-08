package com.nirozjungkarki;

import java.util.List;

public interface PokemonDAO extends DAOInterface<Pokemon ,String> {
	public List<Pokemon> findByType(Type type);
}
