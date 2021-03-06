package forum.bean.data;

import java.util.Date;

public class MessageChiffre extends DecorateurMessage{
	
	public MessageChiffre(Message mess) {
		
		message = mess;
		message.setCrypt(true);
	}
	
	@Override
	public int getIdMessage() {
		return message.getIdMessage();
	}

	@Override
	public Date getDateCreation() {
		return message.getDateCreation();
	}

	@Override
	public String getContenue() {
		return message.getContenue();
	}

	@Override
	public int getDelais() {
		return message.getDelais();
	}

	@Override
	public boolean isPrioritary() {
		return message.isPrioritary();
	}

	@Override
	public boolean isCrypt() {
		return message.isCrypt();
	}

	@Override
	public boolean isWithAccuse() {
		return message.isWithAccuse();
	}
	
	@Override
	public void setDelais(int delais) {
		message.setDelais(delais);		
	}

	@Override
	public void setPrioritary(boolean isPrioritary) {
		message.setPrioritary(isPrioritary);		
	}

	@Override
	public void setCrypt(boolean isCrypt) {
		message.setCrypt(isCrypt);
	}

	@Override
	public void setWithAccuse(boolean withAccuse) {
		message.setWithAccuse(withAccuse);		
	}
	

}
