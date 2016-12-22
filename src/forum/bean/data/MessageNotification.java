package forum.bean.data;

public class MessageNotification extends Notification{
	
	private String type;
	private int idGroupe;
	
	public MessageNotification(int id, User envoyeur, User receveur, int idGroupe) {
		super(id, envoyeur, receveur);
		this.type = "message";
		this.idGroupe = idGroupe;
	}

	public int getIdGroupe() {
		return idGroupe;
	}

	public void setIdGroupe(int idGroupe) {
		this.idGroupe = idGroupe;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
