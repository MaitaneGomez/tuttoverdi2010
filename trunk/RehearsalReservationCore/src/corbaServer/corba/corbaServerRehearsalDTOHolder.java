package corbaServer.corba;

/**
* corbaServer/corba/corbaServerRehearsalDTOHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from C:/Tutto/RehearsalReservationCore/idl/corbaServer.idl
* lunes 29 de marzo de 2010 17H56' CEST
*/

public final class corbaServerRehearsalDTOHolder implements org.omg.CORBA.portable.Streamable
{
  public corbaServer.corba.corbaServerRehearsalDTO value = null;

  public corbaServerRehearsalDTOHolder ()
  {
  }

  public corbaServerRehearsalDTOHolder (corbaServer.corba.corbaServerRehearsalDTO initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = corbaServer.corba.corbaServerRehearsalDTOHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    corbaServer.corba.corbaServerRehearsalDTOHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return corbaServer.corba.corbaServerRehearsalDTOHelper.type ();
  }

}
