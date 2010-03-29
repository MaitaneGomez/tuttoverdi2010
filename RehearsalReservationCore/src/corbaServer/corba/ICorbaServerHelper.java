package corbaServer.corba;


/**
* corbaServer/corba/ICorbaServerHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from C:/Tutto/RehearsalReservationCore/idl/corbaServer.idl
* lunes 29 de marzo de 2010 17H56' CEST
*/

abstract public class ICorbaServerHelper
{
  private static String  _id = "IDL:corbaServer/corba/ICorbaServer:1.0";

  public static void insert (org.omg.CORBA.Any a, corbaServer.corba.ICorbaServer that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static corbaServer.corba.ICorbaServer extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (corbaServer.corba.ICorbaServerHelper.id (), "ICorbaServer");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static corbaServer.corba.ICorbaServer read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_ICorbaServerStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, corbaServer.corba.ICorbaServer value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static corbaServer.corba.ICorbaServer narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof corbaServer.corba.ICorbaServer)
      return (corbaServer.corba.ICorbaServer)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      corbaServer.corba._ICorbaServerStub stub = new corbaServer.corba._ICorbaServerStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static corbaServer.corba.ICorbaServer unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof corbaServer.corba.ICorbaServer)
      return (corbaServer.corba.ICorbaServer)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      corbaServer.corba._ICorbaServerStub stub = new corbaServer.corba._ICorbaServerStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}