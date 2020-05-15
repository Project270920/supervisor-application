package rtwmas.supervisor.dnmm.model;

public class Participant
{
	private String supervisor_id;
	private Role participantRole;	


	public Participant()	{	}
	
	public Participant(String supervisor_id, Role participantRole)
	{
		this.supervisor_id = supervisor_id;
		this.participantRole = participantRole;
	}


	public String getSupervisor_id() {	return supervisor_id;	}
	public Role getParticipantRole() {	return participantRole;	}


	public Participant setSupervisor_id(String supervisor_id) 		{	this.supervisor_id = supervisor_id;			return this;	}
	public Participant setParticipantRole(Role participantRole) 	{	this.participantRole = participantRole;		return this;	}


	@Override
	public String toString() {
		return "Participant [supervisor_id=" + supervisor_id + ", participantRole=" + participantRole + "]";
	}
}
