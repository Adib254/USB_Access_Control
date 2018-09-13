
namespace CSE323Project
{
    using System.Management;
    public static class WmiExtensions
    {

        /// <summary>
        /// Fetch the first item from the search result collection.
        /// </summary>
        /// <param name="searcher"></param>
        /// <returns></returns>

        public static ManagementObject First(this ManagementObjectSearcher searcher)
        {
            ManagementObject result = null;
            foreach (ManagementObject item in searcher.Get())
            {
                result = item;
                break;
            }
            return result;
        }
    }

}
