package corbaServer.corba.ICorbaServerPackage;


/**
* corbaServer/corba/ICorbaServerPackage/rehearsalsListHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from C:/tutto/RehearsalReservationCore/idl/corbaServer.idl
* domingo 25 de abril de 2010 14H08' CEST
*/

abstract public class rehearsalsListHelper
{
  private static String  _id = "IDL:corbaServer/corba/ICorbaServer/rehearsalsList:1.0";

  public static void insert (org.omg.CORBA.Any a, corbaServer.corba.corbaServerRehearsalDTO[] that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static corbaServer.corba.corbaServerRehearsalDTO[] extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = corbaServer.corba.corbaServerRehearsalDTOHelper.type ();
      __typeCode = org.omg.CORBA.ORB.init ().create_sequence_tc (0, __typeCode);
      __typeCode = org.omg.CORBA.ORB.init ().create_alias_tc (corbaServer.corba.ICorbaServerPackage.rehearsalsListHelper.id (), "rehearsalsList", __typeCode);
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static corbaServer.corba.corbaServerRehearsalDTO[] read (org.omg.CORBA.portable.InputStream istream)
  {
    corbaServer.corba.corbaServerRehearsalDTO value[] = null;
    int _len0 = istream.read_long ();
    value = new corbaServer.corba.corbaServerRehearsalDTO[_len0];
    for (int _o1 = 0;_o1 < value.length; ++_o1)
      value[_o1] = corbaServer.corba.corbaServerRehearsalDTOHelper.read (istream);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, corbaServer.corba.corbaServerRehearsalDTO[] value)
  {
    ostream.write_long (value.length);
    for (int _i0 = 0;_i0 < value.length; ++_i0)
      corbaServer.corba.corbaServerRehearsalDTOHelper.write (ostream, value[_i0]);
  }

}
