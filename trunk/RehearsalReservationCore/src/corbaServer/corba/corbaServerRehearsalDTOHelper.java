package corbaServer.corba;


/**
* corbaServer/corba/corbaServerRehearsalDTOHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from C:/Users/Txemistry/Documents/workspace/Proyecto ISO/RehearsalReservationCore/idl/corbaServer.idl
* jueves 25 de marzo de 2010 17H42' CET
*/

abstract public class corbaServerRehearsalDTOHelper
{
  private static String  _id = "IDL:corbaServer/corba/corbaServerRehearsalDTO:1.0";

  public static void insert (org.omg.CORBA.Any a, corbaServer.corba.corbaServerRehearsalDTO that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static corbaServer.corba.corbaServerRehearsalDTO extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  private static boolean __active = false;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      synchronized (org.omg.CORBA.TypeCode.class)
      {
        if (__typeCode == null)
        {
          if (__active)
          {
            return org.omg.CORBA.ORB.init().create_recursive_tc ( _id );
          }
          __active = true;
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [3];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[0] = new org.omg.CORBA.StructMember (
            "operaName",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[1] = new org.omg.CORBA.StructMember (
            "date",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_long);
          _members0[2] = new org.omg.CORBA.StructMember (
            "seats",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_struct_tc (corbaServer.corba.corbaServerRehearsalDTOHelper.id (), "corbaServerRehearsalDTO", _members0);
          __active = false;
        }
      }
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static corbaServer.corba.corbaServerRehearsalDTO read (org.omg.CORBA.portable.InputStream istream)
  {
    corbaServer.corba.corbaServerRehearsalDTO value = new corbaServer.corba.corbaServerRehearsalDTO ();
    value.operaName = istream.read_string ();
    value.date = istream.read_string ();
    value.seats = istream.read_long ();
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, corbaServer.corba.corbaServerRehearsalDTO value)
  {
    ostream.write_string (value.operaName);
    ostream.write_string (value.date);
    ostream.write_long (value.seats);
  }

}
