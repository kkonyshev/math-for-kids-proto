package mfk.impl;

import mfk.api.Executor;

public class AmountContextExecutor implements Executor<AmountContext> {

	@Override
	public void execute(AmountContext context) {
		System.out.println(context.getContextType() + ": " + context.getNumber());
	}

}
