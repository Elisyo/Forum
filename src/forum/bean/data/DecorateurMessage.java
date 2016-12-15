package forum.bean.data;

import java.util.Date;

public abstract class DecorateurMessage extends Message {

	protected Message message;

	public abstract int getIdMessage();

	public abstract Date getDateCreation();

	public abstract String getContenue();

	public abstract int getDelais();

	public abstract boolean isPrioritary();

	public abstract boolean isCrypt();

	public abstract boolean isWithAccuse();
	
	public abstract void setDelais(int delais);
	
	public abstract void setPrioritary(boolean isPrioritary);
	
	public abstract void setCrypt(boolean isCrypt);
	
	public abstract void setWithAccuse(boolean withAccuse);
}
