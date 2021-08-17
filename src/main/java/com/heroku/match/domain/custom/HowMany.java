package com.heroku.match.domain.custom;

public class HowMany {
	private Long minid;
	private Long maxid;
	private Long howmany;
	public long getMinid() {
		return minid;
	}
	public void setMinid(Long minid) {
		if (minid != null)
			this.minid = minid;
		else
			this.minid = 0L;
	}
	public long getMaxid() {
		return maxid;
	}
	public void setMaxid(Long maxid) {
		if (maxid != null)
			this.maxid = maxid;
		else
			this.maxid = 0L;
	}
	public long getHowmany() {
		return howmany;
	}
	public void setHowmany(Long howmany) {
		this.howmany = howmany;
	}
	
	@Override
	public String toString() {
		return "MinId[" + minid + "], MaxId[" + maxid + "], Count[" + howmany + "]";
	}

}
