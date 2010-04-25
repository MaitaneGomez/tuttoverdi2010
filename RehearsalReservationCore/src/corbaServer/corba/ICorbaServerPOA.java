package corbaServer.corba;


/**
* corbaServer/corba/ICorbaServerPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from C:/tutto/RehearsalReservationCore/idl/corbaServer.idl
* domingo 25 de abril de 2010 14H08' CEST
*/

public abstract class ICorbaServerPOA extends org.omg.PortableServer.Servant
 implements corbaServer.corba.ICorbaServerOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("getRehearsals", new java.lang.Integer (0));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // corbaServer/corba/ICorbaServer/getRehearsals
       {
         corbaServer.corba.corbaServerRehearsalDTO $result[] = null;
         $result = this.getRehearsals ();
         out = $rh.createReply();
         corbaServer.corba.ICorbaServerPackage.rehearsalsListHelper.write (out, $result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:corbaServer/corba/ICorbaServer:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public ICorbaServer _this() 
  {
    return ICorbaServerHelper.narrow(
    super._this_object());
  }

  public ICorbaServer _this(org.omg.CORBA.ORB orb) 
  {
    return ICorbaServerHelper.narrow(
    super._this_object(orb));
  }


} // class ICorbaServerPOA
