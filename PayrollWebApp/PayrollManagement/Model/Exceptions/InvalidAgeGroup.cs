using System;
using System.Runtime.Serialization;

namespace PayrollManagement.Model
{
    [Serializable]
    internal class InvalidAgeGroup : Exception
    {
        public InvalidAgeGroup()
        {
        }

        public InvalidAgeGroup(string message) : base(message)
        {
        }

        public InvalidAgeGroup(string message, Exception innerException) : base(message, innerException)
        {
        }

        protected InvalidAgeGroup(SerializationInfo info, StreamingContext context) : base(info, context)
        {
        }
    }
}