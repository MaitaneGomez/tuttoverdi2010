package corbaServer.corba.ICorbaServerPackage;


/**
* corbaServer/corba/ICorbaServerPackage/rehearsalsListHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from C:/tutto/RehearsalReservationCore/idl/corbaServer.idl
* jueves 29 de abril de 2010 12H05' CEST
*/

public final class rehearsalsListHolder implements org.omg.CORBA.portable.Streamable
{
  public corbaServer.corba.corbaServerRehearsalDTO value[] = null;

  public rehearsalsListHolder ()
  {
  }

  public rehearsalsListHolder (corbaServer.corba.corbaServerRehearsalDTO[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = corbaServer.corba.ICorbaServerPackage.rehearsalsListHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    corbaServer.corba.ICorbaServerPackage.rehearsalsListHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return corbaServer.corba.ICorbaServerPackage.rehearsalsListHelper.type ();
  }

}
