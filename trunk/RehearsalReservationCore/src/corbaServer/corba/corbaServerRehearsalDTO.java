package corbaServer.corba;


/**
* corbaServer/corba/corbaServerRehearsalDTO.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from C:/tutto/RehearsalReservationCore/idl/corbaServer.idl
* martes 11 de mayo de 2010 21H55' CEST
*/

public final class corbaServerRehearsalDTO implements org.omg.CORBA.portable.IDLEntity
{
  public String operaName = null;
  public String date = null;
  public int seats = (int)0;

  public corbaServerRehearsalDTO ()
  {
  } // ctor

  public corbaServerRehearsalDTO (String _operaName, String _date, int _seats)
  {
    operaName = _operaName;
    date = _date;
    seats = _seats;
  } // ctor

} // class corbaServerRehearsalDTO
