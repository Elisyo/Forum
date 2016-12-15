package forum.bean.data;

public class MessageGroup extends Message{
	
	private Groupe groupe;
	
	public MessageGroup() {}
	
	public MessageGroup(String contenue, Groupe groupe) {
		this.groupe = groupe;
		setContenue(contenue);
		setCrypt(false);
		setDelais(0);
		setPrioritary(false);
		setWithAccuse(false);
	}

	public Groupe getGroupe() {
		return groupe;
	}

	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}

}
