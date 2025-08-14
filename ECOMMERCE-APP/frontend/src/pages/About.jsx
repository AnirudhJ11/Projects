import React from 'react'
import Title from '../components/Title'
import { assets } from '../assets/assets'
import NewsletterBox from '../components/NewsletterBox'

const About = () => {
  return (
    <div>

      <div className='text-2x1 text-center pt-8 border-t'>
        <Title text1={'ABOUT'} text2={'US'} />
      </div>

      <div className='my-10 flex flex-col md:flex-row gap-16'>
        <img className='w-full md:max-w-[450px]' src={assets.about_img} alt="" />

        <div className='flex flex-col justify-center gap-6 md:w-2/4 text-gray-600'>
          <p>We are a lifestyle brand committed to redefining everyday essentials with elegance and ease.</p>
          <p>From the very beginning, our goal has been to deliver thoughtfully designed products that blend quality with affordability.</p>
          <b className='text-gray-800'>Our Mission</b>
          <p>To deliver exceptional value and curated collections that enrich daily life through style, functionality, and trust.</p>
        </div>
      </div>

      <div className='text-xl py-4'>
        <Title text1={'WHY'} text2={'CHOOSE US'} />
      </div>

      <div className='flex flex-col md:flex-row text-sm mb-20'>

        <div className='border px-10 md:px-16 py-8 sm:py-20 flex flex-col gap-5'>
          <b>Quality Assurance:</b>
          <p className='text-gray-600' > Each product is handpicked and thoroughly tested to meet our rigorous quality standards before reaching your doorstep.</p>
        </div>

        <div className='border px-10 md:px-16 py-8 sm:py-20 flex flex-col gap-5'>
          <b>Convenience:</b>
          <p className='text-gray-600' >Shopping is seamless and stress-free with our intuitive interface and streamlined checkout experience.</p>
        </div>

        <div className='border px-10 md:px-16 py-8 sm:py-20 flex flex-col gap-5'>
          <b>Exceptional Customer Service:</b>
          <p className='text-gray-600'> Our support team is always ready to help, making sure your experience is smooth from start to finish.</p>
        </div>

        <NewsletterBox/>

      </div>

    </div>
  )
}

export default About;