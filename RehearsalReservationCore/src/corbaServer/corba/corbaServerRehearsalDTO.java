package corbaServer.corba;


/**
* corbaServer/corba/corbaServerRehearsalDTO.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from C:/tutto/RehearsalReservationCore/idl/corbaServer.idl
* mi�rcoles 31 de marzo de 2010 10H25' CEST
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
