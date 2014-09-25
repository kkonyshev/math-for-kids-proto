package kkonyshev.mfk;

import java.util.Date;

/**
 * Действие по элементу
 * 
 * @author kkonyshev
 *
 */
public class Action {
	private ActionType actionType;
	private Date actionDate;
	public Action(ActionType actionType) {
		this.actionType = actionType;
		this.actionDate = new Date();
	}
	public ActionType getActionType() {
		return actionType;
	}
	public void setActionType(ActionType actionType) {
		this.actionType = actionType;
	}
	public Date getActionDate() {
		return actionDate;
	}
	public void setActionDate(Date actionDate) {
		this.actionDate = actionDate;
	}
}
