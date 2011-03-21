package com.quantisan.JFFramework.Samples;

import com.dukascopy.api.IBar;
import com.dukascopy.api.Instrument;
import com.dukascopy.api.JFException;
import com.dukascopy.api.Period;
import com.quantisan.JFFramework.Sentiment;
import com.quantisan.JFFramework.Trade.AbstractEntry;
import com.quantisan.JFFramework.Trade.AbstractExit;
import com.quantisan.JFFramework.Trade.AbstractSetup;
import com.quantisan.JFFramework.Trade.AbstractCondition;
import com.quantisan.JFFramework.Trade.IStop;

public class SingleDualMASetup extends AbstractSetup {
	private Period period;
	private AbstractCondition condition;
	private Sentiment sentiment;
	
	public SingleDualMASetup(AbstractEntry entry, IStop stop, AbstractExit exit, 
						Period period, int fastLength, int slowLength) 
	{
		super(entry, stop, exit);
		this.period = period;
		condition = new DualMovingAveragesCondition(fastLength, slowLength);
	}

	@Override
	public String getTag() {
		return "SDM";
	}

	@Override
	public String toString() {
		return "Single moving average setup";
	}

	@Override
	public void initializeConditions(Instrument instrument) throws JFException {
		sentiment = condition.calculate(instrument, period);

	}

	@Override
	public Sentiment calculate(Instrument instrument, Period period,
			IBar askBar, IBar bidBar) throws JFException {
		if (period == this.period)
			sentiment = condition.calculate(instrument, period);
		return this.sentiment;
	}
}
