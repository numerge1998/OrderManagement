package bll.validators;

import model.Client;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */
public class ClientAgeValidator implements Validator<Client> {
	private static final int MIN_AGE = 5;
	private static final int MAX_AGE = 60;

	public void validate(Client t) {

		if (t.getVarsta() < MIN_AGE || t.getVarsta() > MAX_AGE) {
			throw new IllegalArgumentException("Limita de varsta a clientului este depasita!");
		}

	}

}
