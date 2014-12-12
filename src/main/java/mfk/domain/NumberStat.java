package mfk.domain;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * 
 * @author kkonyshev
 *
 */
public class NumberStat implements Serializable, Comparable<NumberStat> {
	private static final long serialVersionUID = -1848121596397595423L;
	
	private Long profileId;
	private Integer number;
	private Integer count;
	
	public Long getProfileId() {
		return profileId;
	}
	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return profileId + ":[" + number + ";" + count + "]";
	}
	@Override
	public int compareTo(NumberStat o) {
		return this.number.compareTo(o.getNumber());
	}
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(profileId).append(number).hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		NumberStat rigth = (NumberStat)obj;
		return new EqualsBuilder().append(this.profileId, rigth.profileId).append(this.number, rigth.number).isEquals();
	}
}
